# Import Libraries
import tensorflow as tf
import pandas as pd
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import *
from tensorflow.keras.callbacks import ModelCheckpoint
from tensorflow.keras.metrics import RootMeanSquaredError
from tensorflow.keras.optimizers import Adam

tf.keras.utils.set_random_seed(627)

# Import dataset
data = pd.read_csv("ML/Rice price forecasting/data/harga_beras_mingguan.csv")

# Define window size and split data
def windowed_dataset(data, window_size):
    X = []
    y = []
    for i in range(len(data)-window_size):
        row = [a for a in data[i:i+window_size]]
        X.append(row)
        label = data[i+window_size]
        y.append(label)
    return np.array(X), np.array(y).squeeze()

window_size = 12
X, y = windowed_dataset(data, window_size)
X.shape, y.shape

split_time = int(0.9*(len(X)))
val_split = split_time + math.ceil((len(X) - split_time)/2)

X_train, y_train = X[:split_time], y[:split_time]
X_val, y_val = X[split_time:val_split], y[split_time:val_split]
X_test, y_test = X[val_split:], y[val_split:]
X_train.shape, y_train.shape, X_val.shape, y_val.shape, X_test.shape, y_test.shape

# Define the model
model = Sequential()
model.add(InputLayer((12,1)))
model.add(LSTM(256, activation='relu', return_sequences=True))
model.add(Dropout(0.3))
model.add(LSTM(512, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))

model.summary()

checkpoint = ModelCheckpoint('model/', save_best_only=True)
model.compile(loss='mse', optimizer=Adam(learning_rate=0.0001), metrics=[RootMeanSquaredError()])

model.fit(X_train, y_train, validation_data=(X_val, y_val), epochs=50, callbacks=[checkpoint])

from tensorflow.keras.models import load_model
model = load_model('model/')

# Generate the forecast
data.reset_index(inplace=True)
harga_beras = harga_beras.reshape((-1))

def predict(time_ahead, model):
  prediction_list = harga_beras[-12:]
  for _ in range(time_ahead):
    x = prediction_list[-12:]
    x = x.reshape((1, 12, 1))
    result = model.predict(x)[0][0]
    prediction_list = np.append(prediction_list, result)
  prediction_list = prediction_list[11:]
  return prediction_list

def predict_dates(time_ahead):
  last_date = data['tanggal'].values[-1]
  prediction_dates = pd.date_range(last_date, periods=time_ahead+1, freq='W-SAT').tolist()
  return prediction_dates

time_ahead = 12
forecast = predict(time_ahead, model)
forecast_dates = predict_dates(time_ahead)

forecast_temp = pd.DataFrame(data={'forecast' : forecast, 'forecast_dup' : forecast})
forecast_temp = scaler.inverse_transform(forecast_temp)
forecast_temp = pd.DataFrame(list(forecast_temp), columns=["forecast","forecast_dup"])

forecast_results = pd.DataFrame(data={'date' : forecast_dates, 'forecast' : forecast_temp['forecast']})
forecast_results.set_index('date', inplace=True)

def myround(x, base=50):
  return base * round(x/base)

rounded = []
round_index = []
for index, row in forecast_results.iterrows():
  row = int(myround(row))
  rounded.append(row)
  round_index.append(index)

rounded_forecast = pd.DataFrame(data={'date' : round_index, 'rounded forecast' : rounded})
rounded_forecast.set_index('date', inplace=True)
print(rounded_forecast)
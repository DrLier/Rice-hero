# Machine Learning Codebase 🌾

This folder stores the machine learning feature of our application. Listed below are the details about our folders.

### - Rice Disease Classification 🦠

  <p>In the rice disease classification folder, we store the dataset that we use to build the model, model building notebook, and the model py file. </p>
  
  **1. Machine Learning Model**
  <p>
  In Rice Disease Classification, we use the Convolutional Neural Network (CNN) model to classify rice diseases. Convolutional Neural Network (CNN) is one type of neural network that is commonly used in image data. Convolutional Neural Network (CNN) can be used to detect and recognize objects in an image. So, with CNN, our application will be able to detect and classify the type of disease that exists in rice plants through an image. We modelled 4 classes of rice disease, namely : brownspot, blast, leaf blight, and tungro. </p>
  <p>
  We also modelled an additional 1 healthy class as the positive control. We use transfer learning method with InceptionV3 and we are able to reach ... validation accuracy and ... test accuracy based on the model. </p>
  
  **2. Dataset**
  <p> ... 
  </p>
  
### - Rice Price Forecasting 💰
  <p> In the rice price forecasting folder, we store the dataset, model building notebook, and the model py file. </p>
  
  **1. Machine Learning Model**
  <p>
   In this model, we modelled the weekly rice price data from January 2020 until the recent date using LSTM. Long Short Term Memory Network (LSTM) is one of the modifications of Recurrent Neural Network or RNN. There are many modifications of RNN, but LSTM is a popular one among them. LSTM is here to complement the shortcomings of RNN which cannot predict words based on past information stored over a long period of time. As such, LSTMs are able to remember sets of information that have been stored over a long period of time, while removing information that is no longer relevant. LSTM is more efficient in processing, predicting, as well as classifying data based on a specific time sequence.
The final model had the RMSE of 117 and MAPE of 0.92% for data validation, and RMSE of 91 and MAPE of 0.69% for data testing, meaning in average the predicted price had Rp100 difference with the actual price. </p>

**2. Dataset**
  <p> ... 
  </p>


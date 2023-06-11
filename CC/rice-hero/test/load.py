import io
from PIL import Image
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'


model = load_model("model.h5")

# load the image
path = "brownspot.jpg"

img = load_img(path, target_size=(150,150))
img = img_to_array(img)
img = img / 255
imgs = np.expand_dims(img, axis=0)

predictions = model(imgs)
pred = np.argmax(predictions, axis=1)

print(pred)
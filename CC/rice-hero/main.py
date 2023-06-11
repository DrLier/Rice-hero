from flask import Flask, request, jsonify
import numpy as np
from tensorflow import keras
from tensorflow.keras.preprocessing.image import ImageDataGenerator, load_img, img_to_array
import tensorflow as tf
import io
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
from PIL import Image

model = keras.models.load_model("model.h5")


def transform_image(img):
    imgs=[]
    img = img.resize((150,150))
    img = img_to_array(img)
    img = img / 255
    imgs.append(img)
    imgs = np.expand_dims(img, axis=0)
    return imgs

def predict(x):
    predictions = model(x)
    pred = np.argmax(predictions, axis=1)
    return pred

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        file = request.files.get('file')
        if file is None or file.filename == "":
            return jsonify({"error": "no file"})

        try:
            image_bytes = file.read()
            pillow_img = Image.open(io.BytesIO(image_bytes))
            prediction = predict(transform_image(pillow_img))
            data = {"prediction": int(prediction)}
            return jsonify(data)
        except Exception as e:
            return jsonify({"error": str(e)})

    return "OK"


if __name__ == "__main__":
    app.run(debug=True)
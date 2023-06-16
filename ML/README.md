# Machine Learning Codebase 🌾

This folder stores the machine learning feature of our application. Listed below are the details about our folders.

### - Rice Disease Classification 🦠

  <p>In the rice disease classification folder, we store the dataset that we use to build the model, model building notebook, and the model py file. </p>
  
  **1. Machine Learning Model**
  <p>
  In Rice Disease Classification, we use the \[Convolutional Neural Network (CNN)\]\(https://www.tensorflow.org/tutorials/images/cnn\) model to classify rice diseases. Convolutional Neural Network (CNN) is a type of neural network that is commonly used in image data. Convolutional Neural Network (CNN) can be used to detect and recognize objects in an image. So, with CNN, our application will be able to detect and classify the type of disease that exists in rice plants through an image. We modeled 4 classes of [rice diseases](https://www.kaggle.com/datasets/shareef0612/riceleaf-dataset), namely: brown spot, blast, leaf blight, and tungro. We also modeled 1 [healthy rice](https://www.kaggle.com/datasets/gutierrezsoares/rice-leafs-500px) class as a positive control.  </p>
  <p>
  We use [transfer learning](https://www.tensorflow.org/tutorials/images/transfer_learning) method with [InceptionV3](https://keras.io/api/applications/inceptionv3/) and we are able to reach 0.9552 validation accuracy and 0.9195 test accuracy based on the model. We save the model with [.h5]([https://www.tensorflow.org/tutorials/images/transfer_learning](https://www.tensorflow.org/tutorials/keras/save_and_load)) format </p>
  <p>
  The notebooks that we use can be accessed in` [![Open In Collab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/drive/1LTlVFqXsu22dOASi0gcyF6dbU51Pbfqq?usp=sharing) </p>
  
### - Rice Price Forecasting 💰
  <p> In the rice price forecasting folder, we store the dataset, model building notebook, and the model py file. </p>
  
  **1. Machine Learning Model**
  <p>
   In this model, we modelled the weekly rice price data from January 2020 until the recent date using [LSTM](https://www.tensorflow.org/api_docs/python/tf/keras/layers/LSTM). Long Short Term Memory Network (LSTM) is one of the modifications of Recurrent Neural Network or [(RNN)](https://www.tensorflow.org/guide/keras/working_with_rnns). There are many modifications of RNN, but LSTM is a popular one among them. LSTM is here to complement the shortcomings of RNN which cannot predict words based on past information stored over a long period of time. As such, LSTMs are able to remember sets of information that have been stored over a long period of time, while removing information that is no longer relevant. LSTM is more efficient in processing, predicting, as well as classifying data based on a specific time sequence. [The dataset](https://drive.google.com/file/d/1gTJX9XwVjiS_DZkNg3iuxc9dHSVGUJlz/view?usp=sharing) we use is from 2020 to the present day.
The final model had the RMSE of 117 and MAPE of 0.92% for data validation, and RMSE of 91 and MAPE of 0.69% for data testing, meaning in average the predicted price had Rp100 difference with the actual price. </p>
<p>
The notebooks that we use can be accessed in` [![Open In Collab](https://colab.research.google.com/assets/colab-badge.svg)](https://colab.research.google.com/drive/1SvRQxHVNiKjdcH4QP0wGNhTBYxz2HBfc?usp=sharing#scrollTo=hsKgcKUy4ujg) </p>

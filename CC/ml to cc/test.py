import requests

resp = requests.post("https://ricediseaseprediction-jfwoxatv5q-uc.a.run.app/predict",
                     files={'file': open('sehat1.jpg', 'rb')})

print(resp.json())

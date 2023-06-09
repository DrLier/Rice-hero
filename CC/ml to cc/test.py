import requests

resp = requests.post("https://ricediseaseprediction-dumhlsbi3q-et.a.run.app",
                     files={'file': open('brownspot.jpg', 'rb')})

print(resp.json())
import requests

resp = requests.post("https://ricedisease-dumhlsbi3q-as.a.run.app",
                     files={'file': open('brownspot.jpg', 'rb')})

print(resp.json())

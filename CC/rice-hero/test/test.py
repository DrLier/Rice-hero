import requests

resp = requests.post("http://127.0.0.1:5000",
                     files={'file': open('test/brownspot.jpg', 'rb')})

print(resp.json())
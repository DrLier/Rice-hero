import requests

resp = requests.post("https://localhost:5000/",
                     files={'file': open('TUNGRO.jpg', 'rb')})

print(resp.json())

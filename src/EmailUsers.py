# Email users their Secret Santa
from email.message import EmailMessage
import smtplib
import ssl

email_sender = "secretsantageneratormailbot@gmail.com"
password = "lyistjtetsbehxpy"
subject = "Secret Santa Generator"

def email(person1, email1, person2, email2):
    message = "Hello " + person1 + "! Your Secret Santa is: " + person2 + "." + "\n" + "Their email is: " + email2 + "." + "\n" + "Merry Christmas!"
    print(message)
    e = EmailMessage()
    e["from"] = email_sender
    e["to"] = email1
    e["subject"] = subject
    e.set_content(message)

    context = ssl.create_default_context()
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as smtp:
        smtp.login(email_sender, password)
        smtp.sendmail(email_sender, email1, e.as_string())

file = open("Results.txt", "r")
while True:
    line = file.readline()
    if not line:
        break

    # Partitioning Results.txt into person1, email1, person2, email2
    # person1 (email1) -> person2 (email2)
    line = line.strip()
    line = line.split(" -> ")
    line[0].partition(" (")
    person1 = line[0].partition(" (")[0]
    email1 = line[0].partition(" (")[2].partition(")")[0]
    line[1].partition(" (")
    person2 = line[1].partition(" (")[0]
    email2 = line[1].partition(" (")[2].partition(")")[0]

    email(person1, email1, person2, email2)
    
file.close()
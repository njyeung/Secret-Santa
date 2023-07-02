import eel
import os

def runJava():
    # Run the java program and get the exit status

    # Run SecretSanta.java
    exitStatus = os.system("cmd /C \"\"C:\\Program Files\\Java\\jdk-17.0.4.1\\bin\\java.exe\" -cp \"C:\\Users\\nickj\\Desktop\\Secret Santa\\bin\" SecretSanta \"")

    # Wait for program to execute and then read the file
    file = open("People.txt", "r")
    # Read the file and return the contents
    if(file.read() == "sent" and exitStatus == 0):
        return True
    else:
        return False


dirname = os.path.dirname(__file__)
eel.init(os.path.join(dirname, "web/"))

@eel.expose
def generate(array):
    # Write names to file
    file = open("People.txt", "w")
    # Write each name and email to a new line
    for x in range(len(array)):
        if(x % 2 == 0):
            file.write(array[x] + ",")
        else:
            file.write(array[x] + "\n")
    return runJava()

eel.start("index.html")





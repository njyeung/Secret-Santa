import os

# Check if eel is installed
try:
    import eel
except ImportError:
    print("Eel not installed. Installing now...")
    os.system("pip install eel")
    import eel

def runJava():
    # Run the java program and get the exit status
    compile("src/Person.java src/Matches.java src/SecretSanta.java")
    exitStatus = run("-classpath src SecretSanta")

    if(exitStatus == 0):
        return True
    else:
        return False

def compile(javaFile):
    return os.system("javac " + javaFile)

def run(javaFile):
    return os.system("java " + javaFile)

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
    file.close()
    return runJava()

eel.start("index.html")





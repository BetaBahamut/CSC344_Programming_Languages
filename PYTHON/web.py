import webbrowser
import os

f = open('helloworld.html','w')

message = """<html>
<head></head>
<body><p>Hello World!</p></body>
<a href="diff.html">here</a>
</html>"""

f.write(message)
f.close()

filename = 'helloworld.html'
# webbrowser.get('chrome')
webbrowser.open_new_tab(filename)
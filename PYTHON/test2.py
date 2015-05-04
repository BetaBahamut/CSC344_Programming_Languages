import re
import glob
import zipfile
import zlib
import zipfile
import os
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.image import MIMEImage
import mimetypes
from argparse import ArgumentParser
from email import encoders
from email.message import Message
from email.mime.audio import MIMEAudio
from email.mime.base import MIMEBase
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import logging
import logging.handlers
import sys
# from html import HTML



os.chdir("..")
zf = zipfile.ZipFile('CSC344.zip', 'w')
overall = open("CSC344.html",  "w")
overallBody =""
path = os.getcwd()
# dirs = os.listdir( path )
# dirs = filter(os.path.isdir, os.listdir( path ) )
extensions = set([".c",".pl",".lisp",".scala",".py"])
dirs = next(os.walk(path))[1]

# print(file_list)

# This would print all the files and directories
for file in dirs:
    os.chdir(path +"\\"+file)
    internal = open(file +".html",  "w")
    internalBody =""
    # print(path)
    # print(file)
    
    l = list()
    tempL = list()
    
    # a = re.compile('.*/.*\.\(c\|cpp\|h\|pl\|py\|scala\|lisp\)$')
    # print(a.match("stuff.c"))
    for prog in os.listdir(os.getcwd()):
        count = 0
        thing = open(prog)
        if prog.endswith(tuple(extensions)):
            webP = open(prog + ".html",  "w")
            # webP.write("<html><head></head><body><p>")
            whole = ""
            for line in thing:
                whole = whole + line + "<br>"
                count = count + 1
                # print(line)

                # print(line)
                # webP.write(line +"\n")
                # webP.write('\n')
                if prog.endswith(".lisp"):
                    # print(prog)
                    if not(line.startswith(";")):
                        r = re.findall(r"[^ \t\n\r\f\v\"\(\)\\\'\;]*",line)
                    # r = re.findall(r"[_a-zA-Z\S][_a-zA-Z0-9\-\S]*",line)
                else:
                    r = re.findall(r"[_a-zA-Z][_a-zA-Z0-9]*",line)
                for k in r:
                    if(tempL.count(k) == 0):
                        l.append(prog+ " : "+k)
                        tempL.append(k)
                    # if(l.count(k) == 0):
                    #     l.append(k)
            webP.write("<html><head></head><body><p>")
            webP.write(whole)
            webP.write("</p></body></html>")
            webP.close()
            


            # print(whole)
            zf.write(prog)
            zf.write(prog + ".html")
            internalBody = internalBody +"<p>lines: "+str(count) +"<a href=" +prog+".html"+">"+prog+"</a>"+"<br>"
            temp = open(file + "_Idents.txt",  "w")
            # print(prog)
            for w in l:
                temp.write(w+"\n")
            temp.close()
    thing.close()
    # zf.write(prog)
    os.chdir(path +"\\"+file)
    zf.write(file + "_Idents.txt")
    internal.write("<html><head></head><body><p>")
    internal.write(internalBody)
    internal.write("</p></body></html>")
    internal.close()
    zf.write(file +".html")
    overallBody = overallBody + "<a href="+file+".html"+">"+file+"</a>"+"<br>"
os.chdir(path)

overall.write("<html><head></head><body><p>")
overall.write(overallBody)
overall.write("</p></body></html>")
overall.close()
zf.write("CSC344.html")

zf.close()
# print(path)
os.chdir(path)

person = input('Enter the Address: ')
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()
server.login("dtupper@oswego.edu", "ludden16")


#Send the mail
msg = MIMEMultipart()
msg['Subject'] = 'All The Programs'
# me == the sender's email address
# family = the list of all recipients' email addresses
msg['From'] = "dtupper@oswego.edu"
msg['To'] = person

part = MIMEBase('application', 'zip')
fp = open('CSC344.zip', 'rb')
part.set_payload(fp.read())
encoders.encode_base64(part)
part.add_header('Content-Disposition',
                'attachment; filename="%s"' % os.path.basename('CSC344.zip'))
msg.attach(part)


server.send_message(msg)

# os.chdir("..")
# print(os.getcwd())
# print(glob.glob('[_a-zA-Z]*'))
# l = glob.glob('*.*')
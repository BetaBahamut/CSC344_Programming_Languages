import smtplib
import os
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

server = smtplib.SMTP('smtp.gmail.com', 587)

#Next, log in to the server
server.starttls()
server.login("dtupper@oswego.edu", "ludden16")
# server.login(None, None)

#Send the mail
msg = MIMEMultipart()
msg['Subject'] = 'All The Programs'
# me == the sender's email address
# family = the list of all recipients' email addresses
msg['From'] = "dtupper@oswego.edu"
msg['To'] = "dtupper@oswego.edu"

part = MIMEBase('application', 'zip')
fp = open('CSC344.zip', 'rb')
part.set_payload(fp.read())
encoders.encode_base64(part)
part.add_header('Content-Disposition',
                'attachment; filename="%s"' % os.path.basename('CSC344.zip'))
msg.attach(part)


server.send_message(msg)
# server.sendmail("dtupper@oswego.edu", "dtupper@oswego.edu", msg)
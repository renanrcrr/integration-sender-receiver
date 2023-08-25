import socket

xml_data = """
<request>
    <operation>multiply</operation>
    <operands>
        <operand>5</operand>
        <operand>3</operand>
    </operands>
</request>
"""

# Create a socket connection to Java server
host = 'localhost'
port = 12345
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((host, port))

# Send XML data to Java server
sock.send(xml_data.encode())

# Receive and print the response from Java server
response = sock.recv(1024)
print("Received response from Java:", response.decode())

# Close the socket connection
sock.close()

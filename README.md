# 📦 File Packer & Unpacker with Encryption

## 🛠️ Technology Used
- Java Programming

## 📄 Project Overview
This tool is a Java-based file utility that allows users to pack multiple files into a single archive and unpack them later.  
It emphasizes the security feature of encryption, ensuring that only authorized users can decrypt and extract the data.  
Additionally, the project includes a Graphical User Interface (GUI) for ease of use.

## ✨ Key Features

### ✅ File Packing
- Combines multiple regular files into a single archive file.
- Stores metadata (file name, size, timestamp) along with file content.

### ✅ File Unpacking
- Extracts individual files from the packed archive.
- Restores all original metadata and file structure.

### 🔐 Data Security
- Built-in encryption and decryption to protect packed content.

### 🖥️ Graphical User Interface (GUI)
- User-friendly GUI built in Java (Swing).
- Provides simple options for selecting files, encrypting, packing, and unpacking.

### 🌐 Cross-platform Compatibility
- Runs seamlessly on any system with a Java Runtime Environment (JRE).

## 🎓 Learning Outcomes
- Practical experience with Java I/O Streams and File Handling APIs.
- Implementation of metadata management during file operations.
- Strong understanding of encryption/decryption techniques in Java.

## 🚀 How to Run

### 🧵 Command-Line Usage

#### Packing Files
```bash
java GUIPacker <DirectoryName> <OutputArchiveFile>

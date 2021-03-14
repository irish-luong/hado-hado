#include <iostream>
#include <string>

using namespace std;

const int a = 17;
const int b = 20;

string encryptMessage(string text) 
{

    string cipher = "";
    for (int i = 0; i < text.length(); i++) 
    {   

        cout << 'A' << endl;

        if (text[i] != ' ')
        {
            cipher += (char) (((a * (text[i] - 'A') + b) % 26) + 'A');
        } else 
        {
            cipher += text[i];
        }
    }
    return cipher;
}


string decryptMessage(string text) 
{
    string plainText = "";
    int a_inv = 0;
    int flag = 0;

    // Find a^-1 (the multiplicative inverse of a  
        //in the group of integers modulo m)
    for (int i = 0; i < 26; i++) 
    {
        flag = (a * i) % 26;

        if (flag == 1)
        {
            a_inv = i;
        }
    }

    for (int j = 0; j < text.length(); j++)
    {   

        if (text[j] != ' ')
        {
            plainText += (char) (((a_inv * (text[j] + 'A' - b)) % 26) + 'A');
        } else 
        {
            plainText += text[j];
        }
    }

    return plainText;
}

int main(void) 
{   
    string msg = ("AFFINE CIPHER");
    string cipherText = encryptMessage(msg);
    cout << "Cipher text of " << msg << " is " << cipherText << endl;
    cout << "Revert is " << decryptMessage(cipherText) << endl;
}
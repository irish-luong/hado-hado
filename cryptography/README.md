## Cryptography

### Referrence 
- Handbook: [Applied Crytography](http://cacr.uwaterloo.ca/hac/)

### What is cryptography
- Cryptography is the science of using mathematics to encrypt and decrypt data.
- Cryptography enable people to store sensitive information/data or transmit it across insecure networks so that
no one can read it except the intended recipient.
  

### Basic formula
- Encryption rule: e
- Decryption rule: d
- Encryption key: k1
- Decryption key: k2
- Message space:
```
                       dk2(ek1(x)) = x
```

- One-to-one function: One plaintext with the key will encrypt to one ciphertext then one 
ciphertext with the key will decrypt to one plaintext.

### Communication channel
![image info](./encrypt_flow.png)

- Secure channel:

### Cryptosystem
A cryptosystem is a five-tuple (P, C, K, E, D), where:
- P is finite set f possible plaintext
- C is a finite set of possible ciphertexts
- K, the keyspace, set of possible keys


### Classical cipher 
#### The shift cipher: Key space
To be secure: should be very large
To improve the Shift Cipher 

#### The substitution cipher
Let P = C = Z(26) \
We have 26 factorial case when apply substitution cipher

#### Affine cipher
```
                        e(x) = (ax + b) mod 26
                        a,b depend on Z(26)
```
Suppose e(x) = (4x + 7) mod 26

The affine function have unique solution for every z iff
```
                    gcd(a,26) = 1
* with: gcd is the greatest common divisor
```

#### Congruence
a,b are integer; m is a positive integer a === b (mod m), called a congruence, if (a-b) 
divides m.





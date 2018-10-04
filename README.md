# COS330_Ass5
This program takes any ceaser cipher text and attempts to decrypt it using Pearson's Chi Square Test. (Good 'ol stats baby!).

## Compile and Run
Compile with `javac *.java`
Run with `java CeaserCipher <text> <ceaser shift>`

Example: `java CeaserCipher "The Caesar cipher is a special case of the substitution cipher, which maps all possible pieces of plaintext (usually single letters, but not always) to corresponding pieces of ciphertext. There are only 26 Caesar ciphers; on the other hand, there 26! possible letter substitution ciphers.1 Our goal is to crack a Caesar-encrypted message, which means to find its key, the rotation number used to encrypt it. We can easily do this by brute force, by trying all 26 possible keys. The result of decrypting the message will almost certainly be gibberish for all but one key, but how can a computer recognize plausible English?" 10`

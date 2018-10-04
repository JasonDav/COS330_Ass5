public class CeaserCipher{

	public static char[] cShift(char[] text, int shift)
	{
		char[] ntext = new char[text.length];
		// if(shift==0)
		// 	return text;

		if(shift<0)
		{
			shift%=26;
			shift+=26;
		}

		for(int i = 0; i < text.length;i++)
			if(text[i]>='a' && text[i]<='z') ntext[i] = (char) ('a' + ((text[i]-'a'+shift)%26));
			else if(text[i]>='A' && text[i]<='Z') ntext[i] = (char) ('A' + ((text[i]-'A'+shift)%26));
			else ntext[i] = text[i];

		return ntext;
	}

	static final double[] expecFreq = new double[]{8.04, 1.48, 3.34, 3.82, 12.49, 2.40, 1.87, 5.05, 7.57, 0.16, 0.54, 4.07, 2.51,
 												   7.23, 7.64, 2.14, 0.12, 6.28,  6.51, 9.28, 2.73, 1.05, 1.68, 0.23, 1.66, 0.09};

	public static double pearsonChiSquared(double[] obsFreq)
	{
		double xs = 0.0;

		for(int i = 0; i < 26;i++)
			xs+=((obsFreq[i]-expecFreq[i])*(obsFreq[i]-expecFreq[i]))/expecFreq[i];

		return xs;
	}

	public static char[] encrypt(char[] ptext, int shift)
	{
		return cShift(ptext,shift);
	}

	public static double[] getFrequencies(char[] text)
	{
		double[] freq = new double[26];
		int numLetters = 0;

		for(int i = 0; i < text.length;i++)
			if(text[i]>='a' && text[i]<='z'){
				freq[text[i]-'a']++;
				numLetters++;
			}
			else if(text[i]>='A' && text[i]<='Z'){
				freq[text[i]-'A']++; 
				numLetters++;
			}

		for(int i = 0; i < 26; i++){
			freq[i]/=numLetters;
			// System.out.println((char)('a'+i)+": "+freq[i]);
		}

		return freq;
	}

	public static void main(String[] args) {

		// char[] ctext = encrypt(args[0].toCharArray(),10);
		long time = System.currentTimeMillis();
		String text;
		int shift;

		if(args.length>1)
		{
			text = args[0];
			shift = Integer.parseInt(args[1]);
		}
		else
		{
			text = "The Caesar cipher is a special case of the substitution cipher,"
						+" which maps all possible pieces of plaintext (usually single letters, but not always)"
						+" to corresponding pieces of ciphertext. There are only 26 Caesar ciphers; on the other hand, "
						+"there 26! possible letter substitution ciphers. Our goal is to crack a Caesar-encrypted message, "
						+"which means to find its key, the rotation number used to encrypt it. We can easily do this by brute force,"
						+" by trying all 26 possible keys. The result of decrypting the message will almost certainly be gibberish"
						+" for all but one key, but how can a computer recognize plausible English?";
			shift = 10;
		}

		char[] ctext = text.toCharArray();
		ctext = encrypt(ctext,10);
		// System.out.println(ctext);

		// char[] ptext = cShift(ctext,-10);
		// System.out.println(ptext);

		int bestGuess = 0;
		double bestPCS = Double.POSITIVE_INFINITY;
		char[] bestText = ctext;

		for(int i = 1; i <= 26; i++)
		{
			char[] ntext = cShift(ctext,i);
			double pcs = pearsonChiSquared(getFrequencies(ntext));
			// System.out.println(pcs);
			if(pcs<bestPCS){
				bestPCS = pcs;
				bestText=ntext;
				bestGuess = i;
			}
		}

		// System.out.println(pearsonChiSquared(getFrequencies(text.toCharArray())));
		time =System.currentTimeMillis()-time;
		System.out.println("Original Cipher Text: \n");
		System.out.println(ctext);
		System.out.println("\nBest PCS: "+bestPCS+"\nWith Cipher key: "+(26-bestGuess)+"\nWith Text:");
		System.out.println(bestText);
		System.out.println("Time taken: "+time+"ms");

	}
}
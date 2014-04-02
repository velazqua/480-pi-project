import java.io.*;

public class SoundReporter
{
 private String soundFilename;
 private double minAmplitude; 
 private double maxAmplitude; 
 private double midlineAmplitude; 
 private double meanAmplitude;
 private double rmsAmplitude;
 private double roughHz;
 private double mNorm;
 private double maxDelta;
 private double minDelta;
 private double meanDelta;
 private double rDelta;
 private double volumeAdj;
 
 private static String getContents(InputStream in)
 {
	try{
		ByteArrayOutputStream inBytes = new ByteArrayOutputStream();
		
		while (true)
		{
			int b=in.read(); // try reading a single byte.
			if (b>=0)        // if the byte was read, write it to the inBytes stream.
			{
				inBytes.write(b);
			}
			else
				break;      // get out of this while loop.
		}
		
		return new String(inBytes.toByteArray());
	}
	catch (IOException e)
	{
		e.printStackTrace();
		return "";
	}
 }

 /**
 Returns the last double value from the specified string.
 Assumes there is a whitespace just before the double value.
 For example, getDoubleFrom("Maximum amplitude: 	0.992188\n") returns 0.992188
 */
 private static double getDoubleFrom(String s)
 {
	s = s.trim(); // remove the whitespaces at the end of the string.
	
	for (int i=s.length()-1;i>=0;i--)
	{
		if (" \t\r\n".indexOf(s.charAt(i))>=0)
		{
			// for example, Double.parseDouble("0.992188")
			return Double.parseDouble(s.substring(i+1));
		}
	}
	throw new IllegalArgumentException("Unable to find whitespace in: "+s);
 }
 
 private void runSox()
 {
	String content = "";
	try
	{
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("sox "+soundFilename+" -n stat");
		content = getContents(pr.getErrorStream()); // for standard I/O: content = getContents(pr.getInputStream());
	}
	catch (Exception exc)
	{
		exc.printStackTrace();
		System.out.println("Problem running the sox command.");
	}
	
	// loop through lines of content.
	for (String line: content.split("\n"))
	{
		if (line.contains("amplitude") || line.contains("frequency") || line.contains("norm") 
				|| line.contains("delta") || line.contains("adjustment"))
		{
			if (line.contains("Maximum"))
			{
				maxAmplitude=getDoubleFrom(line);
			}
			if (line.contains("Minimum"))
			{
				minAmplitude=getDoubleFrom(line);
			}
			if (line.contains("RMS"))
			{
				rmsAmplitude = getDoubleFrom(line);
			}
			if (line.contains("Mean"))
			{
				meanAmplitude=getDoubleFrom(line);
			}
			if (line.contains("Midline"))
			{
				midlineAmplitude = getDoubleFrom(line);
			}
			if (line.contains("Rough"))
			{
				roughHz = getDoubleFrom(line);
			}
			if (line.contains("Mean"))
			{
				mNorm = getDoubleFrom(line);
			}
			if (line.contains("Maximum"))
			{
				maxDelta = getDoubleFrom(line);
			}
			if (line.contains("Minimum"))
			{
				minDelta = getDoubleFrom(line);
			}
			if (line.contains("Mean"))
			{
				meanDelta = getDoubleFrom(line);
			}
			if (line.contains("RMS"))
			{
				rDelta = getDoubleFrom(line);
			}
			if (line.contains("Volume"))
			{
				volumeAdj = getDoubleFrom(line);
			}
		}
	}
 }

/**
@param filename should be a .wav or .mp3 file.
*/ 
 public SoundReporter(String filename)
 {
	if (!filename.endsWith(".wav")&&!filename.endsWith(".mp3"))
		throw new IllegalArgumentException("Audio file Must be wav or mp3: "+filename);
	
	this.soundFilename=filename;
	runSox();
 }

/**
Incomplete... always returns 0 for now.
*/ 
 public double getDecibels()
 {
	double refAmp=1,measuredAmp=1;
	/*
	What is refAmp supposed to be?  This is unknown right now.
	What is measuredAmp supposed to be?  This is also unknown right now.
	
	Hopefully, the value comes from one of the attributes that we can extract using the sox command.
	*/
	return 20*Math.log10(measuredAmp/refAmp);
 }

/**
Returns in JSON format
*/ 
 public String getJson()
 {
	return "{\"minAmplitude\": "+minAmplitude
			+", \"maxAmplitude\": "+maxAmplitude
			+", \"meanAmplitude\": "+meanAmplitude
			+",\"rmsAmplitude\": "+rmsAmplitude
			+", \"db\": "+getDecibels()
			+", \"rFrequency\": "+roughHz
			+", \"meanNorm\": "+mNorm
			+", \"maxDelta\": "+maxDelta
			+", \"minDelta\": "+minDelta
			+", \"meanDelta\": "+meanDelta
			+", \"rmsDelta\": "+rDelta
			+", \"midlineAmplitude\": "+midlineAmplitude+"}";
 }
 
 /**
 main is for testing that the stats are extracted and printed correctly.
 */
 public static void main(String a[])
 {
	if (a.length<1)
	{
		System.out.println("wav or mp3 file must be specified.");
	}
	else
	{
		SoundReporter reporter = new SoundReporter(a[0]);
		System.out.println(""+reporter.getJson());
	}
	
 } // end main
 
} // end class SoundReporter

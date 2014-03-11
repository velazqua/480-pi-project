/*
 * Pi RMI.
 */
package pirmi;

/**
 * Parses output from -n stat option of sox
 * @author Alex Velazquez
 */
public class Stats
{
    private double maxAmp, minAmp, meanAmp;
    private double maxDelta, minDelta, meanDelta;
    private double freq, db;

    public Stats(String output)
    {
        this.maxAmp = getMaxAmp(output);
        this.minAmp = getMinAmp(output);
        this.meanAmp = getMeanAmp(output);
        this.maxDelta = getMaxDelta(output);
        this.minDelta = getMinDelta(output);
        this.meanDelta = getMeanDelta(output);
        this.freq = getFrequency(output);
        this.db = calculateDecibels(this.freq);
    }

    private double getMaxAmp (String output) {}
    private double getMinAmp (String output) {}
    private double getMeanAmp (String output) {}
    private double maxDelta (String output) {}
    private double minDelta (String output) {}
    private double meanDelta (String output) {}
    private double getFrequency (String output) {}
    private double calculateDecibels (double f) {}

    public JSONObject getJSONObject () {
    }
}

package places;

import placeAttributes.Address;

public class NightSpot extends Place{
    private boolean alcohol;
    private boolean liveMusic;

    public NightSpot(String name, Address address, boolean alcohol, boolean liveMusic) {
        super(name, address);
        this.alcohol = alcohol;
        this.liveMusic = liveMusic;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public boolean isLiveMusic() {
        return liveMusic;
    }
}

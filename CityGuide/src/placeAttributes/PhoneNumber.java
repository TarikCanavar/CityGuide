package placeAttributes;
public class PhoneNumber {
    private int countryCode;
    private int code;
    private int number;

    public PhoneNumber(int countryCode, int code, int number) {
        this.countryCode = countryCode;
        this.code = code;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getCode() {
        return code; 
    }

    public int getNumber() {
        return number;
    }
    public void setCountryCode(int countryCode) {
		this.countryCode = countryCode; 
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
    public String toString() {
        return "+" + String.valueOf(code)+" "+ countryCode  + " " + String.valueOf(number);
    }
}

public class Token {
    private String tokenId;
    private int tokenValue;
    private String tokenPart;

    public String getTokenId() {
        return tokenId;
    }

    public int getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(int tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getTokenPart() {
        return tokenPart;
    }
    public Token(String tokenId,int tokenValue,String tokenPart){
        this.tokenId = tokenId;
        this.tokenValue = tokenValue;
        this.tokenPart = tokenPart;
    }
}

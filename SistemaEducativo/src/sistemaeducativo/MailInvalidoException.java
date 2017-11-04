package sistemaeducativo;

public class MailInvalidoException extends Exception{
    private String mail;
    public MailInvalidoException(String mail) {
        this.mail=mail;
    }


    public String getMail() {
        return mail;
    }
}

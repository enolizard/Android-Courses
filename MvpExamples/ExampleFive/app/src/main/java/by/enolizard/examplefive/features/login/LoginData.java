package by.enolizard.examplefive.features.login;

class LoginData {
    private String login;
    private String pass;

    LoginData(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}

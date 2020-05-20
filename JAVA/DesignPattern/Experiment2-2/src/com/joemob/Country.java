abstract public class Country {
    UN UnitedNations;

    abstract void setUN(UN UnitedNations);

    abstract void declare(String declaration, Country country);

    abstract void getMessage(String message);
}

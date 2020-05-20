public class WTO implements UN {
    private Country CN;
    private Country US;

    WTO(Country cn, Country us) {
        CN = cn;
        US = us;
    }

    @Override
    public void declare(String declaration, Country country) {
        country.getMessage(declaration);
    }
}

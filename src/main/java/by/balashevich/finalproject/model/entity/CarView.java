package by.balashevich.finalproject.model.entity;

public class CarView {
    private String exterior;
    private String exteriorSmall;
    private String interior;

    public String getExteriorSmall() {
        return exteriorSmall;
    }

    public void setExteriorSmall(String exteriorSmall) {
        this.exteriorSmall = exteriorSmall;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarView carView = (CarView) o;

        return (exterior != null && exterior.equals(carView.exterior))
                && (exteriorSmall != null && exteriorSmall.equals(carView.exteriorSmall))
                && (interior != null && interior.equals(carView.interior));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + (exterior == null ? 0 : exterior.hashCode());
        result += 37 * result + (exteriorSmall == null ? 0 : exteriorSmall.hashCode());
        result += 37 * result + (interior == null ? 0 : interior.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return String.format("View: exterior %s, exteriorSmall %s, interior %s", exterior, exteriorSmall, interior);
    }
}

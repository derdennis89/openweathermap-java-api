package org.openweathermap.api.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openweathermap.api.model.Coordinate;

@EqualsAndHashCode(callSuper = true)
@Data
public class InCycle extends AbstractQuery implements CurrentWeatherManyLocationsQuery {
    private Coordinate centerPoint;
    private int expectedNumberOfCities;
    private Cluster cluster;

    public InCycle(Coordinate centerPoint, int expectedNumberOfCities) {
        this.centerPoint = centerPoint;
        this.expectedNumberOfCities = expectedNumberOfCities;
    }

    @Override
    protected String getSearchPath() {
        return "/find";
    }

    @Override
    protected String getRequestPart() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("lat=").append(centerPoint.getLatitude()).append(AND);
        stringBuilder.append("lon=").append(centerPoint.getLongitude()).append(AND);
        stringBuilder.append("cnt=").append(expectedNumberOfCities);
        if (cluster != null) {
            stringBuilder.append(AND).append("cluster=").append(cluster.getStringRepresentation());
        }
        return stringBuilder.toString();
    }
}

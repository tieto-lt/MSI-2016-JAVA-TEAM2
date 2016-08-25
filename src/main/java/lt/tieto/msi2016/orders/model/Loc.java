package lt.tieto.msi2016.orders.model;

import java.math.BigDecimal;

/**
 * Created by localadmin on 16.8.25.
 */
public class Loc {
        private BigDecimal x;
        private BigDecimal y;
        private BigDecimal z;
        private BigDecimal yaw;

    public Loc(BigDecimal bigDecimal, BigDecimal bigDecimal1, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
        setX(bigDecimal);
        setY(bigDecimal1);
        setZ(bigDecimal2);
        setYaw(bigDecimal3);
    }




    public BigDecimal getX() {
            return x;
        }

        public void setX(BigDecimal x) {
            this.x = x;
        }

        public BigDecimal getY() {
            return y;
        }

        public void setY(BigDecimal y) {
            this.y = y;
        }

        public BigDecimal getZ() {
            return z;
        }

        public void setZ(BigDecimal z) {
            this.z = z;
        }

        public BigDecimal getYaw() {
            return yaw;
        }

        public void setYaw(BigDecimal yaw) {
            this.yaw = yaw;
        }
}

package entity;

import java.io.Serializable;

public class ProductInfo implements Serializable{
	private static final long serialVersionUID = -2991298280989446708L;
		private String productId;
        private int totalQuantity;

        public ProductInfo(String productId, int totalQuantity) {
            this.productId = productId;
            this.totalQuantity = totalQuantity;
        }

        public String getProductId() {
            return productId;
        }

        public int getTotalQuantity() {
            return totalQuantity;
        }
    
}

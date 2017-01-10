package com.laichushu.book.bean.wechatpay;

public class WxInfo
{

	/**
	 * success : true
	 * data : {"status":"1","partnerId":"1350796301","prepayId":"wx20160905181942fdd66a2e820351790169","nonceStr":"1819424019","timeStamp":"1473070784","packageName":"Sign=WXPay","sign":"7363CE51454112703337BB2324A23B49"}
	 */

	private boolean success;
	/**
	 * status : 1
	 * partnerId : 1350796301
	 * prepayId : wx20160905181942fdd66a2e820351790169
	 * nonceStr : 1819424019
	 * timeStamp : 1473070784
	 * packageName : Sign=WXPay
	 * sign : 7363CE51454112703337BB2324A23B49
	 */

	private DataBean data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public static class DataBean {
		private String status;
		private String partnerId;
		private String prepayId;
		private String nonceStr;
		private String timeStamp;
		private String packageName;
		private String sign;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPartnerId() {
			return partnerId;
		}

		public void setPartnerId(String partnerId) {
			this.partnerId = partnerId;
		}

		public String getPrepayId() {
			return prepayId;
		}

		public void setPrepayId(String prepayId) {
			this.prepayId = prepayId;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		@Override
		public String toString() {
			return "DataBean{" +
					"status='" + status + '\'' +
					", partnerId='" + partnerId + '\'' +
					", prepayId='" + prepayId + '\'' +
					", nonceStr='" + nonceStr + '\'' +
					", timeStamp='" + timeStamp + '\'' +
					", packageName='" + packageName + '\'' +
					", sign='" + sign + '\'' +
					'}';
		}
	}
}

import {WooPaymentType} from './woo-payment-type';
export class WooPayment {
  id: number;
  wooPaymentType: WooPaymentType;
  fileName: string;
  paypalPaymentID: string;
};

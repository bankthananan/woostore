import {OrderItem} from './order-item';
import {User} from '../user/user';
import {TransactionStatus} from './transaction-status';
import {WooPayment} from './woo-payment';
export class Transaction {
  id: number;
  items: OrderItem[];
  status: TransactionStatus;
  owner: User;
  wooPayment: WooPayment;
  date: Date;
  totalPrice: number;
}

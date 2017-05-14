import {OrderItem} from './order-item';
import {User} from '../user/user';
import {TransactionStatus} from './transaction-status';
export class Transaction {
  id: number;
  items: OrderItem[];
  status: TransactionStatus;
  owner: User;
  // wooPayment
  date: Date;
  totalPrice: number;
}

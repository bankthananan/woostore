import {OrderItem} from './orderItem';
import {User} from '../user/user';
export class Transaction {
  id: number;
  items: OrderItem[];
  owner: User;
  // wooPayment
  date: Date;
  totalPrice: number;
}

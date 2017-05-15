import {UserAuth} from './user-auth';
export class User {
  id: number;
  firstName: string;
  lastName: string;
  address: string;
  phoneNumber: string;
  userAuth: UserAuth;
  enabled: boolean = true;
}

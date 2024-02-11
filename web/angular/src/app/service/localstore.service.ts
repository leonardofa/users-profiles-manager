export default class Utils {
  static cleanAuth(){
    localStorage.removeItem('userId');
    localStorage.removeItem('userIdentification');
    localStorage.removeItem('userProfile');
    localStorage.removeItem('userProfile');
  }

}

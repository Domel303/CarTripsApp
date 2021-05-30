import axios from "axios";
const userKey = "user";
const SERVER_PREFIX = process.env.REACT_APP_BASE_URI

const AuthenticationService = {
  signIn : function (username, password) {
      return axios.post(`${SERVER_PREFIX}/api/auth/signin`, {username, password})
        .then(response => {
          if (response.data.accessToken) {
            let value = JSON.stringify(response.data)
            localStorage.setItem(userKey,value);
          }
          return response.data;
        })
        .catch(err => {
          throw err;
        });
  },

  signOut : function() {
    localStorage.removeItem(userKey);
  },

  register : function async(user)  {
    return axios.post(`${SERVER_PREFIX}/api/auth/signup`, user);
  },
  isSignedIn : function async () { return localStorage.getItem(userKey) !== null },

  isAdmin : function async () {
    if(!this.isSignedIn) return false

    let admin = false
    this.getCurrentUser().authorities.forEach(authority => { if (authority.authority === "ROLE_ADMIN") admin = true })
    return admin
  },

  getCurrentUser : function async () { return JSON.parse(localStorage.getItem(userKey)) }

}

export default AuthenticationService;
import '../../styles/general.scss';
import '../../styles/mui.scss';
import './auth.scss';
import {changeErrorInput, get} from '../../scripts/dom';
import {checkForError} from '../../scripts/helpers';
import {loginNameValidators, passwordValidators} from '../../scripts/validators';

const loginName = get('#login');
const password = get('#password');
const form = get('#form');

form.addEventListener('submit', event => {
  event.preventDefault();

  changeErrorInput(loginName, '', true, '.login-wrapper');
  changeErrorInput(password, '', true, '.password-wrapper');

  if (!loginName || !loginName.value || !password || !password.value) return;

  const loginError = checkForError(loginName.value, loginNameValidators);
  const passwordError = checkForError(password.value, passwordValidators);

  loginError && changeErrorInput(loginName, loginError, !loginError, '.login-wrapper');
  passwordError && changeErrorInput(password, passwordError, !passwordError, '.password-wrapper');
});
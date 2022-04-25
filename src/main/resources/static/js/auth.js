function changeErrorInput(input, errorMessage = '', clear = false, parentOfErrorMessagesSelector = '') {
  try {
    if (!input) return;
    if (clear) {
      input.classList.remove('error');
      if (parentOfErrorMessagesSelector) {
        const closest = input.closest(parentOfErrorMessagesSelector);
        if (closest) {
          closest.querySelectorAll('span.error-msg').forEach(span => span.remove());
        }
      }
      return;
    }

    if (!errorMessage) return;

    input.classList.add('error');
    input.insertAdjacentHTML('afterend', `
      <span class="error-msg">${errorMessage}</span>
    `)

    return;
  } catch (e) {
    console.warn(e);
  }
}

const isEmpty = (value) => {
  try {
    return {
      valid: !!value,
      message: 'Ничего не введено'
    };
  } catch (e) {
    console.warn(e);
  }
};

const isMoreThanMin = (min = 4) => (value) => {
  try {
    return {
      valid: value && value.length >= min,
      message: 'Минимальная длина ' + min
    };
  } catch (e) {
    console.warn(e);
  }
}

const isMoreThanMax = (max = 16) => (value) => {
  try {
    return {
      valid: value && value.length <= max,
      message: 'Максимальная длина ' + max
    };
  } catch (e) {
    console.warn(e);
  }
}

function checkForError(value, validators) {
  try {
    if (value === undefined || !validators) return;

    for (const validator of validators) {
      const result = validator(value);
      if (!result.valid) return result.message;
    }

    return '';
  } catch (e) {
    console.warn(e);
  }
}

const passwordValidators = [isEmpty, isMoreThanMin(4)];
const loginNameValidators = [isEmpty, isMoreThanMin(6), isMoreThanMax()];

const loginName = document.querySelector('#login');
const password = document.querySelector('#password');
const form = document.querySelector('#form');

form.addEventListener('submit', event => {

  changeErrorInput(loginName, '', true, '.login-wrapper');
  changeErrorInput(password, '', true, '.password-wrapper');

  const loginError = checkForError(loginName.value, loginNameValidators);
  const passwordError = checkForError(password.value, passwordValidators);

  loginError && changeErrorInput(loginName, loginError, !loginError, '.login-wrapper');
  passwordError && changeErrorInput(password, passwordError, !passwordError, '.password-wrapper');

  if (loginError || passwordError) {
    event.preventDefault();
  }
});
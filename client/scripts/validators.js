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

export const passwordValidators = [isEmpty, isMoreThanMin(4)];
export const loginNameValidators = [isEmpty, isMoreThanMin(6), isMoreThanMax()];
//return index of the element in the passed array
export function loopFindHelper(array, whatToFind) {
  try {
    if (!array || !Array.isArray(array) || !whatToFind) return;

    for (let i = 0; i < array.length; i++) {
      if (array[i].toLowerCase() === whatToFind.toLowerCase()) {
        return i;
      }
    }
    return null;
  } catch (e) {
    console.warn(e);
  }
}

export function checkForError(value, validators) {
  try {
    if (!value || !validators) return;

    for (const validator of validators) {
      const result = validator(value);
      if (!result.valid) return result.message;
    }

    return '';
  } catch (e) {
    console.warn(e);
  }
}
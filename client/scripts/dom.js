import {loopFindHelper} from './helpers';

export const defineThePage = () => {
  try {
    const nav = document.querySelector('header .nav');
    if (!nav) return;

    let links = nav.querySelectorAll('a[data-to]');
    if (!links.length) return;
    links = Array.from(links);

    if (!document.location.pathname.split('/').length) return;

    const splittedPathname = document.location.pathname.split('/');
    const file = splittedPathname[splittedPathname.length - 1];
    const splittedFile = file.split('.');

    if (!splittedFile.length || !splittedFile[0]) return;

    const page = splittedFile[0];

    for (const link of links) {
      const { to } = link.dataset;

      if (!to) return;

      if (to.indexOf(',') !== -1) {
        const splittedAttrTo = to.split(',').filter(s => !!s);
        const index = loopFindHelper(splittedAttrTo, page);
        if (!index) return;
        return link.classList.add('bold');
      } else {
        if (to.toLowerCase() === page.toLowerCase()) {
          return link.classList.add('bold');
        }
      }
    }
  } catch (e) {
    console.warn(e)
  }
};

export function get(selector, all = false) {
  try {
    return all ? document.querySelectorAll(selector) : document.querySelector(selector);
  } catch (e) {
    console.warn(e);
  }
}

export function changeErrorInput(input, errorMessage = '', clear = false, parentOfErrorMessagesSelector = '') {
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


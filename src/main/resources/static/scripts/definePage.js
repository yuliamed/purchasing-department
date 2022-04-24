try {
  document && document.addEventListener('DOMContentLoaded', onLoad);
} catch (e) {
  console.warn(e);
}

function onLoad() {
  defineThePage();
}

function defineThePage() {
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
    const {to} = link.dataset;

    if (!to) return;

    if (to.indexOf(',') !== -1) {
      const splittedAttrTo = to.split(',').filter(s => !!s);
      const index = loopFindHelper(splittedAttrTo, page);
      if (!index) return;
      return link.classList.add('current');
    } else {
      if (to.toLowerCase() === page.toLowerCase()) {
        return link.classList.add('current');
      }
    }
  }
}

function loopFindHelper(array, whatToFind) {
  if (!array || !Array.isArray(array) || !whatToFind) return;

  for (let i = 0; i < array.length; i++) {
    if (array[i].toLowerCase() === whatToFind.toLowerCase()) {
      return i;
    }
  }
  return null;
}


(function() {
  onLoad();

  let newRowsToAdd = [];
  let selectedRows = [];
  let isInProcess = false;

  const onAddRowSave = () => {
  };
  const onRemoveRowSave = () => {
  };
  const onEditRowSave = () => {
  };
  const onRefreshRowSave = () => {
  };

  function onLoad() {
    const table = getTablesWithActions();

    // const mutationObserver = new MutationObserver(() => {
    //   onLoad();
    // });
    //
    // mutationObserver.observe(table, {
    //   childList: true,
    //   subtree: true,
    // });

    getEditButtons(table);
  }

  function getTablesWithActions() {
    const table = document.querySelector('.content-table')
    const thead = table.querySelector('thead');
    const tbody = table.querySelector('tbody');

    const trs = tbody.querySelectorAll('tr');

    const forbiddenSelected = ['select', 'input', 'button', 'option'];

    trs.forEach((tr, i) => {
      if (tr.classList.contains('selected')) selectedRows.push({
        node: tr, index: i
      });
      tr.onclick = e => {
        if (forbiddenSelected.includes(e.target.tagName.toLowerCase())) return;
        toggleTableSelectedRow(tr, i);
      };
    });

    return table;
  }

  function getEditButtons(table, cancel) {
    const buttonsEditWrapper = document.querySelector('.content-edit-buttons');
    if (!buttonsEditWrapper) return;
    const btns = Array.from(buttonsEditWrapper.children);
    if (cancel) {
      return btns.forEach(btn => btn.onclick = null);
    }

    btns.forEach(btn => {
      btn.onclick = e => defineBtnAction(e.target, table, btns);
    });
  }

  function toggleTableSelectedRow(tr, index) {
    if (tr && typeof index === 'number') {
      tr.classList.toggle('selected');
      if (tr.classList.contains('selected')) selectedRows.push({
        node: tr, index
      });
      else selectedRows = selectedRows.filter(t => t.node !== tr);
    }
  }

  function clearSelectedRows() {
    selectedRows.forEach(row => toggleClasses(row.node, 'remove', 'selected'));
    selectedRows = [];
  }

  function toggleProcess(hardValue = '') {
    if (typeof hardValue === 'boolean') return isInProcess = hardValue;
    isInProcess = !isInProcess;
  }

  function toggleBtn(btn, btns, removeAll) {
    if (btns) {
      btns.forEach(b => {
        if (removeAll) {
          return toggleClasses(b, 'remove', 'selected');
        }
        if (b !== btn) toggleClasses(b, 'remove', 'selected');
      });
    }
    if (removeAll) return;
    if (btn && btn.dataset.action) {
      btn.classList.toggle('selected');
    }
  }

  function defineBtnAction(btn, table, btns) {
    let correctBtn = btn;
    if (!btn.dataset?.action) correctBtn = btn.closest('[data-action]');
    if (!correctBtn) return;
    const {action} = correctBtn.dataset;

    switch (action) {
      case 'add':
        toggleProcess(true);
        addTableRow(table, btns);
        return toggleBtn(correctBtn, btns);
      case 'edit':
        if (isInProcess) return;
        selectedRows.length && toggleProcess();
        if (selectedRows.length) {
          editTableRow(table, btns);
          toggleBtn(correctBtn, btns);
        }
        return;
      case 'remove':
        if (isInProcess) return;
        selectedRows.length && toggleProcess();
        if (selectedRows.length) {
          removeTableRow(table, btns);
          toggleBtn(correctBtn, btns);
        }
        return;
      case 'refresh':
        refreshTable(table, btns);
        toggleBtn(correctBtn, btns);
      default:
        return;
    }
  }

  function addTableRow(table, btns) {
    const tbody = table.querySelector('tbody');
    const thead = table.querySelector('thead');
    const ths = [...thead.children[0].children];
    const checkMarkIndex = ths.findIndex(th => 'checkmark' in th.dataset);
    const resourceIndex = ths.findIndex(th => 'resources' in th.dataset);
    const orderStatusIndex = ths.findIndex(th => 'statuses' in th.dataset);
    const tdsLength = tbody.children[0].children.length;

    const newTr = document.createElement('tr');
    newTr.className = 'progress';
    const inputsValues = {};

    let tdWithCheckMark = null;
    let tdWithResource = null;
    let tdWithOrderStatus = null;
    if (checkMarkIndex > -1) {
      tdWithCheckMark = document.createElement('td');
      const input = document.createElement('input');
      input.type = 'checkbox';
      inputsValues[checkMarkIndex] = true;
      input.checked = inputsValues[checkMarkIndex];
      input.addEventListener('input', e => {
        inputsValues[checkMarkIndex] = e.target.checked;
      });
      tdWithCheckMark.append(input);
    }
    if (resourceIndex > -1) {
      inputsValues[resourceIndex] = [];
      const list = inputsValues[resourceIndex];
      const resources = ths[resourceIndex].dataset.resources.split(',');

      if (resources.length) {
        tdWithResource = document.createElement('td');
        const div = document.createElement('div');
        addStyles(div, { display: 'grid', gap: '10px' });
        const button = document.createElement('button');
        button.className = 'btn btn-gray';
        button.innerText = 'Добавить ресурс';

        button.addEventListener('click', () => {
          const divInner = document.createElement('div');
          divInner.className = 'w-resource';
          const select = document.createElement('select');
          const input = document.createElement('input');
          input.type = 'number';
          input.value = 1;
          select.value = resources[0];

          list.push({
            input: {node: input, value: +input.value},
            select: {node: select, value: resources[0]},
          });

          const listIndex = list.length - 1;

          input.addEventListener('input', e => {
            list[listIndex].input.value = +e.target.value;
          });

          select.addEventListener('change', e => {
            list[listIndex].select.value = e.target.value;
          });

          resources.forEach(res => {
            const option = document.createElement('option');
            option.value = res.toLowerCase();
            option.innerText = res;

            select.append(option);
          });

          divInner.append(select);
          divInner.append(input);
          div.append(divInner);
        });

        tdWithResource.append(button);
        tdWithResource.append(div);
      }
    }
    if (orderStatusIndex > -1) {
      const statuses = ths[orderStatusIndex].dataset.statuses.split(',');
      if (statuses.length) {
        const select = document.createElement('select');
        select.className = 'with-arrow';
        inputsValues[orderStatusIndex] = select.value = statuses[0];

        statuses.forEach(status => {
          const option = document.createElement('option');
          option.value = status.toLowerCase();
          option.innerText = status;
          select.append(option);
        });

        select.addEventListener('change', e => {
          inputsValues[orderStatusIndex] = e.target.value;
        });

        tdWithOrderStatus = document.createElement('td');
        tdWithOrderStatus.className = 'select-wrapper';
        tdWithOrderStatus.append(select);
      }
    }

    for (let i = 0; i < tdsLength; i++) {
      if (i === checkMarkIndex && tdWithCheckMark) {
        newTr.append(tdWithCheckMark);
        continue;
      }
      if (i === resourceIndex && tdWithResource) {
        newTr.append(tdWithResource);
        continue;
      }
      if (i === orderStatusIndex && tdWithOrderStatus) {
        newTr.append(tdWithOrderStatus);
        continue;
      }

      const td = document.createElement('td');
      const input = document.createElement('input');
      input.type = 'text';
      inputsValues[i] = '';
      input.addEventListener('input', e => {
        inputsValues[i] = e.target.value;
      });
      td.append(input);
      newTr.append(td);
    }

    newRowsToAdd.push({
      node: newTr,
      inputsValues,
      checkMarkIndex,
      resourceIndex,
      orderStatusIndex
    });

    tbody.append(newTr);

    const onSave = () => {
      newRowsToAdd.forEach(row => {
        const trToSave = document.createElement('tr');
        for (const value in row.inputsValues) {
          let isComplexValue = false;
          let tdToSave = document.createElement('td');
          if ('checkMarkIndex' in row && row.checkMarkIndex > -1 && +value === checkMarkIndex) {
            isComplexValue = true;
            tdToSave.innerText = '';
            tdToSave.className = row.inputsValues[value] ? 'check-mark-active' : '';
          }
          if ('resourceIndex' in row && row.resourceIndex > -1 && +value === resourceIndex) {
            isComplexValue = true;
            const resourcesUl = document.createElement('ul');
            resourcesUl.className = 'resources-list';
            tdToSave.className = 'resources-wrapper';
            tdToSave.append(resourcesUl);

            let resourceElements = '';
            for (const valueObject of row.inputsValues[value]) {
              resourceElements += `<li>
              <div class="material">${valueObject.select.value}</div>
              <div class="amount">${valueObject.input.value}</div>
            </li>`;
            }
            resourcesUl.innerHTML = resourceElements;
          }
          if ('orderStatusIndex' in row && row.orderStatusIndex > -1 && +value === orderStatusIndex) {
            isComplexValue = true;
            tdToSave = tdWithOrderStatus;
          }
          if (!isComplexValue) {
            tdToSave.innerText = inputsValues[value];
          }
          trToSave.append(tdToSave);
        }
        row.node.remove();
        tbody.append(trToSave);
      });
      newRowsToAdd = [];
      toggleBtn(null, btns, true);
      toggleProcess();

      onAddRowSave();
    };

    const onCancel = () => {
      newRowsToAdd.forEach(row => row.node.remove());
      newRowsToAdd = [];
      toggleBtn(null, btns, true);
      toggleProcess();
    };

    removeSaveBtn();
    addSaveButton(table, onSave, onCancel);
  }

  function editTableRow(table, btns) {
    const tbody = table.querySelector('tbody');
    const thead = table.querySelector('thead');
    if (!tbody) return;
    const tdsLength = tbody.children[0].children.length;
    const ths = [...thead.children[0].children];
    const checkMarkIndex = ths.findIndex(th => 'checkmark' in th.dataset);
    const resourceIndex = ths.findIndex(th => 'resources' in th.dataset);
    const orderStatusIndex = ths.findIndex(th => 'statuses' in th.dataset);

    let trsBeforeDelete = {};
    let trsToChange = {};

    const len = () => Object.values(trsToChange).length;
    const config = () => ({
      saveName: `Изменить ${len()}`,
      withoutSaveBtn: !len()
    });

    const onSave = () => {
      for (const trKey in trsBeforeDelete) {
        trsBeforeDelete[trKey].remove();
      }
      for (const trKey in trsToChange) {
        for (const inpKey in trsToChange[trKey].inputsValues) {
          const neededNode = trsToChange[trKey].node.children[inpKey];
          if (!neededNode || neededNode.tagName !== 'TD') continue;
          let isComplexChange = false;
          if (checkMarkIndex > -1 && +inpKey === checkMarkIndex) {
            isComplexChange = true;
            neededNode.innerText = '';
            neededNode.className = trsToChange[trKey].inputsValues[inpKey] ? 'check-mark-active' : '';
          }
          if (resourceIndex > -1 && +inpKey === resourceIndex) {
            isComplexChange = true;
            neededNode.className = 'resources-wrapper';
            const ul = document.createElement('ul');
            ul.className = 'resources-list';
            neededNode.append(ul);

            let resourcesElementsHtml = '';
            trsToChange[trKey].inputsValues[inpKey].forEach(data => {
              resourcesElementsHtml += `<li>
              <div class="material">${data.material}</div>
              <div class="amount">${data.amount}</div>
            </li>`;
            });

            ul.innerHTML = resourcesElementsHtml;

            neededNode.innerHTML = '';
            neededNode.append(ul);
          }
          if (orderStatusIndex > -1 && +inpKey === orderStatusIndex) {
            isComplexChange = true;
          }
          if (!isComplexChange) {
            neededNode.innerText = trsToChange[trKey].inputsValues[inpKey];
          }
        }
      }
      toggleBtn(null, btns, true);
      clearSelectedRows();
      toggleProcess();

      onEditRowSave();
    };

    const onCancel = () => {
      for (const trKey in trsToChange) {
        trsToChange[trKey].node.remove();
      }
      for (const trKey in trsBeforeDelete) {
        trsBeforeDelete[trKey].style.display = '';
      }
      toggleBtn(null, btns, true);
      clearSelectedRows();
      toggleProcess();
    };

    selectedRows.forEach(value => {
      const {index, node: tr} = value;
      toggleClasses(tr, 'remove', 'selected');
      tr.style.display = 'none';
      trsBeforeDelete[index] = tr;

      const newTr = document.createElement('tr');
      newTr.className = 'progress';
      trsToChange[index] = {
        node: newTr,
        inputsValues: {}
      };
      for (let i = 0; i < tdsLength; i++) {
        let isComplexChange = false;
        const td = document.createElement('td');
        if (checkMarkIndex > -1 && i === checkMarkIndex) {
          isComplexChange = true;
          const input = document.createElement('input');
          input.type = 'checkbox';
          input.value = tr.children[i].checked;
          trsToChange[index].inputsValues[i] = input.value;
          td.append(input);
        }
        if (resourceIndex > -1 && i === resourceIndex) {
          isComplexChange = true;
          const resources = ths[resourceIndex].dataset.resources.split(',');
          if (!resources.length) continue;
          const resourcesUl = tr.querySelector('ul.resources-list');
          const resourcesElements = [...resourcesUl.querySelectorAll('li')].map(li => {
            const material = li.querySelector('.material').innerText;
            const amount = li.querySelector('.amount').innerText;
            return {
              material, amount
            };
          });
          const addButton = document.createElement('button');
          addButton.className = 'btn btn-gray';
          addButton.innerText = 'Добавить ресурс';
          const divWrapper = document.createElement('div');
          addStyles(divWrapper, { display: 'grid', gap: '20px' });

          td.append(addButton, divWrapper);

          let divs = [];

          const helper = (resource, iteration) => {
            const divInner = document.createElement('div');
            const select = document.createElement('select');
            const input = document.createElement('input');
            const removeBtn = document.createElement('button');

            trsToChange[index].inputsValues[i].push({
              material: resource.material,
              amount: +resource.amount,
              index: iteration
            });

            const currentIndex = trsToChange[index].inputsValues[i].length - 1;

            divInner.className = 'w-resource';
            addStyles(divInner, { gridTemplateColumns: '1fr 100px 40px' })

            select.value = resource.material;

            input.type = 'number';
            input.value = +resource.amount;

            removeBtn.className = 'btn btn-red';
            removeBtn.innerText = '×';

            select.addEventListener('change', e => {
              trsToChange[index].inputsValues[i][currentIndex].material = e.target.value;
            });

            input.addEventListener('input', e => {
              trsToChange[index].inputsValues[i][currentIndex].amount = +e.target.value;
            });

            removeBtn.addEventListener('click', e => {
              if (divs.length === 1) return;
              trsToChange[index].inputsValues[i] = trsToChange[index].inputsValues[i].splice(currentIndex, 1);
              divs = divs.filter(d => d !== divInner);
              divInner.remove();
            });

            resources.forEach(res => {
              const option = document.createElement('option');
              option.value = res.toLowerCase();
              option.innerText = res;
              select.append(option);
            });

            divInner.append(select, input, removeBtn);
            divs.push(divInner);

            return divInner;
          };

          addButton.addEventListener('click', e => {
            const divInner = helper({
              material: resources[0],
              amount: 1
            }, divs.length);
            divWrapper.append(divInner);
          });

          trsToChange[index].inputsValues[i] = [];

          resourcesElements.forEach((res, i) => {
            const divInner = helper(res, i);
            divWrapper.append(divInner);
          });
        }
        if (orderStatusIndex > -1 && orderStatusIndex === i) {
          isComplexChange = true;
          const statuses = ths[orderStatusIndex].dataset.statuses.split(',');
          if (!statuses.length) continue;

          const select = document.createElement('select');
          select.className = 'with-arrow';
          trsToChange[index].inputsValues[i] = tr.children[orderStatusIndex].querySelector('select.with-arrow').value || '';

          statuses.forEach(status => {
            const option = document.createElement('option');
            option.value = status.toLowerCase();
            option.innerText = status;
            select.append(option);
          });

          select.addEventListener('change', e => {
            trsToChange[index].inputsValues[i] = e.target.value;
          });

          td.className = 'select-wrapper';
          td.append(select);
        }
        if (!isComplexChange) {
          const input = document.createElement('input');
          input.type = 'text';
          input.value = tr.children[i].innerText || '';
          trsToChange[index].inputsValues[i] = input.value;
          input.addEventListener('input', e => {
            trsToChange[index].inputsValues[i] = e.target.value;
          });
          td.append(input);
        }
        newTr.append(td);
      }

      tr.insertAdjacentElement('afterend', newTr);
      removeSaveBtn();
      addSaveButton(table, onSave, onCancel, config());
    });
  }

  function removeTableRow(table, btns) {
    const tbody = table.querySelector('tbody');
    if (!tbody) return;
    const trs = tbody.querySelectorAll('tr');

    const trsToDelete = {};

    const helper = () => {
      toggleBtn(null, btns, true);
      removeSaveBtn();
      clearSelectedRows();
    };

    const len = () => selectedRows.length;

    const config = () => ({
      saveName: `Удалить ${len()}`,
      withoutSaveBtn: !len()
    });

    const onSave = () => {
      selectedIndex = []
      selectedRows.forEach(row => {
        selectedIndex.push(row.node.innerText.split('\t')[1]);
        row.node.remove();
      });
      $.ajax({
        type: 'DELETE',
        url: '/'+page+'/delete', // адрес запроса
        data: {'delIds':selectedIndex}, // данные запроса
        dataType: 'text', // тип ожидаемых данных,
        success: function(data) {
          alert(data)
          console.log(selectedIndex);
        }, // обработка ответа от сервера
        error: function(jqXHR) { console.log('Ошибка выполнения'); },
        complete: function() { console.log('Завершение выполнения'); }
      });
      helper();
      toggleProcess();

      onRemoveRowSave();
    };

    const onCancel = () => {
      selectedRows.un;
      helper();
      toggleProcess();
    };

    addSaveButton(table, onSave, onCancel, config());
  }

  function refreshTable(table, btns) {
    isInProcess = false;
    selectedRows.forEach(row => row.node.classList.remove('selected'));
    newRowsToAdd.forEach(row => row.node.remove());
    selectedRows = [];
    newRowsToAdd = [];
    removeSaveBtn();
    getEditButtons(null, true);
    refreshScript('scripts/tableControl.js');
    setTimeout(() => toggleBtn(null, btns, true), 200);
  }

  function addSaveButton(table, callbackOnSave, callbackOnCancel, options) {
    const div = document.createElement('div');
    div.className = 'save-wrapper';

    const btn = document.createElement('button');
    btn.className = 'save btn btn-blue';
    if (options && options.saveName) {
      btn.innerText = options.saveName;
    } else {
      btn.innerText = 'Сохранить';
    }

    btn.addEventListener('click', e => {
      callbackOnSave && callbackOnSave();
      div.remove();
    });

    if (options) {
      options.withoutSaveBtn ? btn.remove() : div.append(btn);
    } else {
      div.append(btn);
    }

    if (callbackOnCancel) {
      const btnCancel = document.createElement('button');
      btnCancel.className = 'cancel btn btn-red';
      btnCancel.innerText = 'Отменить';

      div.classList.add('space-between');

      btnCancel.addEventListener('click', e => {
        div.remove();
        callbackOnCancel();
      });

      div.prepend(btnCancel);
    }

    table.insertAdjacentElement('afterend', div);
  }

  function removeSaveBtn() {
    document.querySelector('.save-wrapper')?.remove();
  }

  function toggleClasses(node, option = 'remove', ...classes) {
    if (node) {
      classes.forEach(cl => {
        if (option === 'remove') return node.classList.remove(cl);
        else if (option === 'add') return node.classList.add(cl);
      });
    }
  }

  function addStyles(node, styles) {
    if (node && styles) {
      for (const styleKey in styles) {
        node.style[styleKey] = styles[styleKey];
      }
    }
  }

  function refreshScript(scriptSrc) {
    let scripts = document.head.querySelectorAll('script');
    let tableScript = [...scripts].find(script => script.src.indexOf(scriptSrc) > -1);
    if (tableScript) {
      let url = tableScript.src;
      tableScript.remove();

      const newScript = document.createElement('script');
      newScript.src = url;
      newScript.setAttribute('defer', '');
      document.head.append(newScript);
    }
  }
  let saveBtn = document.getElementById("save-btn");
  saveBtn.addEventListener('click', function (e) {
    //e.preventDefault();
    selectedIndex = []
    selectedRows.forEach(row => {
      selectedIndex.push(row.node.innerText.split('\t')[1]);
    });
    console.log(selectedIndex)
    if (selectedIndex.length < 1) {
      alert("Для создания продукта выберите хотя бы 1 ресурс")
      e.preventDefault();
    } else {
      document.getElementById("specifications").value = selectedIndex;
    }
  });
})();
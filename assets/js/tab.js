// tab script
(function () {
  "use strict";

  const tabGroups = document.querySelectorAll("[data-tab-group]");
  const tablist = document.querySelectorAll("[data-tab-nav] [data-tab]");

  const mainArticle = document.querySelector('.content-top .main-article');
  const contentMain = document.querySelector('.tab .content-main');

  function setActiveTab(tabGroup, tabName) {
    const tabsNav = tabGroup.querySelector("[data-tab-nav]");
    const tabsContent = tabGroup.querySelector("[data-tab-content]");

    tabsNav.querySelectorAll("[data-tab]").forEach((tabNavItem) => {
      tabNavItem.classList.remove("active");
    });
    tabsContent.querySelectorAll("[data-tab-panel]").forEach((tabPane) => {
      tabPane.classList.remove("active");
    });

    const selectedTabNavItem = tabsNav.querySelector(`[data-tab="${tabName}"]`);
    selectedTabNavItem.classList.add("active");
    const selectedTabPane = tabsContent.querySelector(`[data-tab-panel="${tabName}"]`);
    selectedTabPane.classList.add("active");

    const selectedTag = selectedTabPane.getAttribute('data-tab-tag');

    // reset: everything is visible at first
    mainArticle.classList.remove('hidden');
    contentMain.querySelectorAll("[data-tags]").forEach((pane) => {
      pane.classList.remove('hidden');
    });

    // then, make visible only those with the tag
    if (selectedTag !== 'all') {
      function hideIfNotTagged(element) {
        if (!element.getAttribute('data-tags').split(",").some(e => e.trim() === selectedTag)) {
          element.classList.add('hidden');
        }
      }

      hideIfNotTagged(mainArticle);
      contentMain.querySelectorAll("[data-tags]").forEach((pane) => hideIfNotTagged(pane));
    }
  }

  tabGroups.forEach((tabGroup) => {
    const tabsNav = tabGroup.querySelector("[data-tab-nav]");
    const tabsNavItem = tabsNav.querySelectorAll("[data-tab]");
    const activeTabName = tabsNavItem[0].getAttribute("data-tab");

    setActiveTab(tabGroup, activeTabName);

    tabsNavItem.forEach((tabNavItem) => {
      tabNavItem.addEventListener("click", () => {
        const tabName = tabNavItem.dataset.tab;
        setActiveTab(tabGroup, tabName);
      });
    });
  });

  function tabsHandler(event) {
    let index = Array.from(tablist).indexOf(this);
    let numbTabs = tablist.length;
    let nextId;
    if (numbTabs > 1) {
      if (event.key === "ArrowRight") {
        nextId = tablist[(index + 1) % numbTabs];
        if (index === numbTabs - 1) {
          nextId = tablist[0];
        }
        nextId.focus();
        nextId.click();
      }
      if (event.key === "ArrowLeft") {
        nextId = tablist[(index - 1 + numbTabs) % numbTabs];
        if (index === 0) {
          nextId = tablist[numbTabs - 1];
        }
        nextId.focus();
        nextId.click();
      }
    }
  }

  tablist.forEach(function (tab) {
    tab.addEventListener("keydown", tabsHandler);
  });
})();

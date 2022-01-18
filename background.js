var tabList = [];
// On update adding event listener
chrome.tabs.onUpdated.addListener(function (tabId , info) {
    if (info.status === 'complete') {
        // Query for available tabs
        chrome.tabs.query({active: true, currentWindow: true}, tabs => {
            // Fetching all cookies available in current window
            chrome.cookies.getAll({path: "/"}, (cookies) => {
                // Execute script to store cookies in local storage
                chrome.scripting.executeScript({
                    target: {tabId: tabs[0].id, allFrames: true},
                    args: [JSON.stringify(cookies)],
                    func: (cookies) => {
                        localStorage.setItem("allCookies", cookies);
                    }
                }); 
            });
        });
    }
})
// Check if any invalid tabs available
chrome.tabs.query({active: true, currentWindow: true}, tabs => {
    tabList = tabs;
});
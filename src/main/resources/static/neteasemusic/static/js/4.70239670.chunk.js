(this["webpackJsonpreact-music"]=this["webpackJsonpreact-music"]||[]).push([[4],{529:function(e,t,n){var i=n(530);"string"===typeof i&&(i=[[e.i,i,""]]);var a={insert:"head",singleton:!1};n(18)(i,a);i.locals&&(e.exports=i.locals)},530:function(e,t,n){(e.exports=n(17)(!1)).push([e.i,".rank {\n  position: fixed;\n  width: 100%;\n  top: 88px;\n  bottom: 0;\n}\n.rank .toplist {\n  height: calc(100vh - 89px - 60px);\n  overflow: hidden;\n}\n.rank .toplist .item {\n  display: flex;\n  margin: 0 20px;\n  padding-top: 20px;\n  height: 100px;\n}\n.rank .toplist .item:last-child {\n  padding-bottom: 20px;\n}\n.rank .toplist .item .icon {\n  flex: 0 0 100px;\n  width: 100px;\n  height: 100px;\n}\n.rank .toplist .item .songlist {\n  flex: 1;\n  display: flex;\n  flex-direction: column;\n  justify-content: center;\n  padding: 0 20px;\n  height: 100px;\n  overflow: hidden;\n  background: #333;\n  color: rgba(255,255,255,0.3);\n  font-size: 12px;\n}\n.rank .toplist .item .songlist .song {\n  text-overflow: ellipsis;\n  overflow: hidden;\n  white-space: nowrap;\n  line-height: 36px;\n}\n.rank .toplist .item .songlist .song .title {\n  font-size: 14px;\n  color: rgba(254,252,252,0.659);\n}\n.rank .toplist .loading-container {\n  position: absolute;\n  width: 100%;\n  top: 50%;\n  transform: translateY(-50%);\n}\n",""])},533:function(e,t,n){"use strict";n.r(t);var i=n(15),a=n(12),r=n(0),o=n.n(r),c=n(25),l=n(76),s=n.n(l),p=(n(529),n(50)),f=n(140);function h(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,i)}return n}t.default=function(e){var t=this,n=Object(r.useState)([]),l=Object(a.a)(n,2),m=l[0],u=l[1],d=Object(r.useRef)(null),g=Object(r.useRef)(null);function b(t){console.info(t),e.history.push({pathname:"/rank/".concat(t.id),state:{playlist:t}})}return Object(r.useEffect)((function(){Object(p.e)().then((function(e){console.info(e),u(e.data.map((function(e){return function(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?h(n,!0).forEach((function(t){Object(i.a)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):h(n).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}({},e,{picUrl0:e.picUrl,picUrl:Object(f.a)(e.picUrl,"200y200")})})))}))}),[]),o.a.createElement("div",{className:"rank",ref:g},o.a.createElement(c.f,{className:"toplist",reference:d},o.a.createElement("ul",null,m.map((function(e,n){return o.a.createElement("li",{onClick:b.bind(t,e),key:"rank".concat(e.id),className:"item"},o.a.createElement("div",{className:"icon"},o.a.createElement(s.a,{placeholder:o.a.createElement(c.c,{height:50,width:50}),scrollContainer:"#o-scroll"},o.a.createElement("img",{width:"100",height:"100",src:e.picUrl}))),o.a.createElement("ul",{className:"songlist"},o.a.createElement("li",{className:"song"},o.a.createElement("h2",{className:"title"},e.title),o.a.createElement("p",{className:"desc"},e.desc))))}))),o.a.createElement("div",{className:"loading-container","v-show":"!topList.length"})))}}}]);
//# sourceMappingURL=4.70239670.chunk.js.map
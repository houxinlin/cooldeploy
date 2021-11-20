var C=Object.defineProperty;var m=Object.getOwnPropertySymbols;var P=Object.prototype.hasOwnProperty,k=Object.prototype.propertyIsEnumerable;var b=(e,s,t)=>s in e?C(e,s,{enumerable:!0,configurable:!0,writable:!0,value:t}):e[s]=t,g=(e,s)=>{for(var t in s||(s={}))P.call(s,t)&&b(e,t,s[t]);if(m)for(var t of m(s))k.call(s,t)&&b(e,t,s[t]);return e};import{g as j,a as V,b as H,c as I}from"./system.0125b4c6.js";import{_ as R}from"./plugin-vue_export-helper.5a098b48.js";import{C as A,l as D,m as B,r as c,o as N,g as z,h as a,w as o,v as l,j as d,t as E,k as y,x as T,y as L}from"./vendor.9ddb015a.js";import"./axiso.b9dc8a44.js";import"./index.fcb59b53.js";const M={mounted(){this.getPub()},components:{CopyDocument:A},setup(){const e=D({sshPub:"",passwd:""}),s=()=>{navigator.clipboard.writeText(e.sshPub).then(function(){l({message:"\u590D\u5236\u6210\u529F.",type:"success",duration:800})},function(n){})},t=()=>{j().then(n=>{e.sshPub=n.data.data}),V().then(n=>{e.passwd=n.data.data.login_passwd})};return g({copySSH:s,getPub:t,savePasswd:()=>{I({passwd:e.passwd}).then(n=>{l({message:n.data.data,type:"success",duration:800})})},generatorRsa:()=>{H().then(n=>{t(),l({message:n.data.data,type:"success",duration:800})})}},B(e))}},h=e=>(T("data-v-45a32b8e"),e=e(),L(),e),U=y("\u751F\u6210SSH\u516C\u94A5"),q={class:"item"},F={class:"card-header"},G=h(()=>d("span",null,"\u5F53\u524D\u516C\u94A5",-1)),J={class:"content"},K={class:"item"},O=h(()=>d("div",{class:"card-header"},[d("span",null,"\u8BBE\u7F6E\u767B\u9646\u5BC6\u7801")],-1)),Q=y("\u4FDD\u5B58\u5BC6\u7801");function W(e,s,t,p,f,n){const r=c("el-button"),v=c("CopyDocument"),w=c("el-icon"),_=c("el-card"),u=c("el-tab-pane"),S=c("el-input"),x=c("el-tabs");return N(),z("div",null,[a(x,{type:"border-card"},{default:o(()=>[a(u,{label:"SSH\u516C\u94A5"},{default:o(()=>[a(r,{onClick:p.generatorRsa,type:"primary",size:"mini"},{default:o(()=>[U]),_:1},8,["onClick"]),d("div",q,[a(_,{style:{"margin-top":"10px"},class:"box-card"},{header:o(()=>[d("div",F,[G,d("span",{onClick:s[0]||(s[0]=(...i)=>p.copySSH&&p.copySSH(...i)),class:"point-style"},[a(w,null,{default:o(()=>[a(v)]),_:1})])])]),default:o(()=>[d("div",J,E(e.sshPub),1)]),_:1})])]),_:1}),a(u,{label:"\u7CFB\u7EDF\u5BC6\u7801"},{default:o(()=>[d("div",K,[a(_,{style:{"margin-top":"10px"},class:"box-card"},{header:o(()=>[O]),default:o(()=>[a(S,{type:"password",modelValue:e.passwd,"onUpdate:modelValue":s[1]||(s[1]=i=>e.passwd=i)},null,8,["modelValue"]),a(r,{style:{"margin-top":"10px"},type:"primary",onClick:p.savePasswd,size:"mini"},{default:o(()=>[Q]),_:1},8,["onClick"])]),_:1})])]),_:1})]),_:1})])}var te=R(M,[["render",W],["__scopeId","data-v-45a32b8e"]]);export{te as default};

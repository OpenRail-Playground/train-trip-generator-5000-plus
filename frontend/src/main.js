import './style.css'
import javascriptLogo from './javascript.svg'
import viteLogo from '/vite.svg'
import { setupCounter } from './counter.js'
import "@db-ux/core-components/build/styles/rollup.css";
import { defineCustomElements } from "@db-ux/wc-core-components";
defineCustomElements();

document.querySelector('#app').innerHTML = `
  <form>
    <db-button>Button</db-button>
    <db-input placeholder="Input"></db-input>
    <db-textarea placeholder="Textarea"></db-textarea>
    <db-select placeholder="Select">
      <option value="1">Option 1</option>
      <option value="2">Option 2</option>
      <option value="3">Option 3</option>
    </db-select>
    <db-checkbox>Checkbox</db-checkbox>
    <db-radio name="radio-group" value="1">Radio 1</db-radio>
    <db-radio name="radio-group" value="2">Radio 2</db-radio>
  </form>
`

setupCounter(document.querySelector('#counter'))

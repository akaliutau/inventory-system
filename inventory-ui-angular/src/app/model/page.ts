import {Item} from "./item";

export interface Page {
  totalElements: number;
  content: Item[];
  number: number;
  size: number;
  numberOfElements: number;
  totalPages: number;
}

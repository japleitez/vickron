import dayjs from 'dayjs/esm';

import { CompanyState } from 'app/entities/enumerations/company-state.model';

import { ICompany, NewCompany } from './company.model';

export const sampleWithRequiredData: ICompany = {
  id: 32440,
  country: 'Holy See (Vatican City State)',
  name: 'Triple-buffered Functionality',
  state: CompanyState['CREATED'],
};

export const sampleWithPartialData: ICompany = {
  id: 56983,
  country: 'Honduras',
  name: 'Oval turquoise Falkland',
  state: CompanyState['CREATED'],
  license: 'intermediate',
  phone: '1-396-525-5148',
};

export const sampleWithFullData: ICompany = {
  id: 72984,
  country: 'Canada',
  name: 'RAM withdrawal',
  state: CompanyState['CLOSE'],
  license: 'Dynamic navigate Cambridgeshire',
  start: dayjs('2023-01-02T02:17'),
  end: dayjs('2023-01-01T15:28'),
  email: 'Brendon.Witting@hotmail.com',
  phone: '871.658.8478 x131',
  address: 'Cambridgeshire Squares',
  billingPhone: 'optimizing deliver',
  billingEmail: '1080p',
  billingAddress: 'generating Avon National',
  vatin: 'wireless Frozen Home',
};

export const sampleWithNewData: NewCompany = {
  country: 'Macedonia',
  name: 'Central',
  state: CompanyState['CREATED'],
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

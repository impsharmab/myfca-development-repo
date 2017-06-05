import { MyfcadashboardPage } from './app.po';

describe('myfcadashboard App', () => {
  let page: MyfcadashboardPage;

  beforeEach(() => {
    page = new MyfcadashboardPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
